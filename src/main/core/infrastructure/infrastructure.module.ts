import { Global, Module } from "@nestjs/common";
import { ElasticsearchModule } from "@nestjs/elasticsearch";
import { ElasticsearchConfiguration } from "../../../main/config/elasticsearch.config";

@Global()
@Module({
  exports: [ElasticsearchModule],
  imports: [
    ElasticsearchModule.registerAsync({
      useClass: ElasticsearchConfiguration
    })
  ]
})
export class InfrastructureModule {}
