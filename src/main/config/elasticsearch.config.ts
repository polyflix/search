import { ClientOptions } from "@elastic/elasticsearch";
import { BasicAuth } from "@elastic/transport/lib/types";
import { Injectable } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { ElasticsearchOptionsFactory } from "@nestjs/elasticsearch";

@Injectable()
export class ElasticsearchConfiguration implements ElasticsearchOptionsFactory {
  constructor(private readonly configService: ConfigService) {}

  createElasticsearchOptions(): ClientOptions | Promise<ClientOptions> {
    return {
      nodes: this.clusterNodes,
      auth: this.auth
    };
  }

  private get auth(): BasicAuth {
    return {
      username: this.configService.get("elasticsearch.auth.username"),
      password: this.configService.get("elasticsearch.auth.password")
    };
  }

  private get clusterNodes(): string[] {
    const nodes = this.configService.get("elasticsearch.nodes");
    if (typeof nodes === "string") {
      return nodes.split(",");
    }
    return nodes;
  }
}
