import { Controller, Get, Inject, Logger } from "@nestjs/common";
import { ClientKafka, EventPattern } from "@nestjs/microservices";
import { KAFKA_CLIENT } from "../messaging.module";

@Controller({
  version: "2"
})
export class EventController {
  @Inject(KAFKA_CLIENT)
  private readonly client: ClientKafka;
  private readonly logger = new Logger(EventController.name);

  @EventPattern("polyflix.legacy.video")
  handleEvent(payload: string) {
    this.logger.log(`Received event on topic`);
    console.log(payload);
  }

  @Get()
  async find() {
    return this.client.emit(
      "polyflix.legacy.video",
      JSON.stringify({ ok: "done" })
    );
  }
}
