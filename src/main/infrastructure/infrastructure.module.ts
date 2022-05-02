import { Global, Inject, Module, OnModuleInit } from "@nestjs/common";
import { ElasticsearchModule } from "@nestjs/elasticsearch";
import { ClientKafka } from "@nestjs/microservices";
import { TerminusModule } from "@nestjs/terminus";
import { OpenTelemetryModule } from "nestjs-otel";
import { ElasticsearchConfiguration } from "../config/elasticsearch.config";
import { HttpModule } from "./http/http.module";
import { KAFKA_CLIENT, MessagingModule } from "./messaging/messaging.module";

@Global()
@Module({
  imports: [
    HttpModule,
    TerminusModule,
    MessagingModule,
    HttpModule,
    OpenTelemetryModule.forRoot(),
    ElasticsearchModule.registerAsync({
      useClass: ElasticsearchConfiguration
    })
  ]
})
export class InfrastructureModule implements OnModuleInit {
  @Inject(KAFKA_CLIENT)
  private readonly client: ClientKafka;

  async onModuleInit() {
    await this.client.connect();
  }
}
