import { Controller, Get, Logger, Query } from "@nestjs/common";

@Controller({
  version: "2"
})
export class HttpController {
  private readonly logger = new Logger(HttpController.name);

  @Get("search")
  public search(@Query() query: any) {
    this.logger.log("Search with query = " + JSON.stringify(query));
  }
}
