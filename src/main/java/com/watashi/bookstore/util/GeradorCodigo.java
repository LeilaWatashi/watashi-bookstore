package com.watashi.bookstore.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GeradorCodigo {

	public static String gerarCodigoPedido() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour()
				+ localDateTime.getMinute()
				+ localDateTime.getSecond()
				+ localDateTime.getDayOfYear());
		String codigo = random.nextInt(89) + 10 + "-" + (random.nextInt(8999) + 1000);
		return codigo;
	}
	
	public static String gerarCodigoCupom() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour()
				+ localDateTime.getMinute()
				+ localDateTime.getSecond()
				+ localDateTime.getDayOfYear());
		String codigo = random.nextInt(899999) + 100000 + "-" + (random.nextInt(89) + 10);
		return codigo;
	}

	public static String gerarCodigoRastreio() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour()
				+ localDateTime.getMinute()
				+ localDateTime.getSecond()
				+ localDateTime.getDayOfYear());
		String codigo = "BR" + (random.nextInt(899999999) + 100000000) + "AA";
		return codigo;
	}

	public static String gerarCodigoTransacao() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour()
				+ localDateTime.getMinute()
				+ localDateTime.getSecond()
				+ localDateTime.getDayOfYear());
		String codigo = random.nextInt(899) + 100 + "-" + (random.nextInt(899) + 100);
		return codigo;
	}

	public static String gerarCodigoLivro() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour()
				+ localDateTime.getMinute()
				+ localDateTime.getSecond()
				+ localDateTime.getDayOfYear());
		String codigo = String.valueOf(random.nextInt(89999999)+10000000);
		return codigo;
	}

	public static String gerarCodigoUsuario() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour()
				+ localDateTime.getMinute()
				+ localDateTime.getSecond()
				+ localDateTime.getDayOfYear());
		String codigo = String.valueOf(random.nextInt(899999999)+100000000);
		return codigo;
	}
}