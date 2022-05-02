import { KafkaOptions, Transport } from "@nestjs/microservices";

export const getKafkaConfiguration = (
  config: Record<string, any>
): KafkaOptions => ({
  transport: Transport.KAFKA,
  options: {
    client: {
      clientId: config["client"]["clientId"],
      brokers: config["client"]["brokers"].map((v) => `${v.host}:${v.port}`)
    },
    consumer: {
      groupId: config["consumer"]["groupId"],
      allowAutoTopicCreation: config["consumer"]["allowAutoTopicCreation"]
    }
  }
});
