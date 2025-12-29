import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { ValidationPipe } from '@nestjs/common';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.useGlobalPipes(
    new ValidationPipe({
      transform: true,
      whitelist: true,
      forbidNonWhitelisted: true
    })
  );

  const config = new DocumentBuilder()
    .setTitle('API Target Sistemas')
    .setDescription('API de Cálculo de Comissão, Movimentação de Estoque e Cálculo de Juros ')
    .setVersion('1.0.0')
    .addTag('sales')
    .build();

  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('docs', app, document);
    
  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
