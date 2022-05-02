import { Global, Module } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { ClientProxyFactory } from "@nestjs/microservices";
import { getKafkaConfiguration } from "../../config/kafka.config";

export const KAFKA_CLIENT = "KAFKA_CLIENT";

@Global()
@Module({
  exports: [KAFKA_CLIENT],
  providers: [
    {
      provide: KAFKA_CLIENT,
      inject: [ConfigService],
      useFactory: (configService: ConfigService) => {
        return ClientProxyFactory.create(
          getKafkaConfiguration(configService.get("kafka"))
        );
      }
    }
  ]
})
export class MessagingModule {}
