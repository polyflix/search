import { DynamicModule, Logger } from "@nestjs/common";
import { ConfigModule } from "@nestjs/config";
import { InfrastructureModule } from "./infrastructure/infrastructure.module";

interface AppModuleOptions {
  config?: Record<string, any>;
}

export class AppModule {
  static bootstrap(options?: AppModuleOptions): DynamicModule {
    return {
      module: AppModule,
      providers: [Logger],
      imports: [
        InfrastructureModule,
        ConfigModule.forRoot({
          isGlobal: true,
          load: [() => options?.config || {}]
        })
      ]
    };
  }
}
