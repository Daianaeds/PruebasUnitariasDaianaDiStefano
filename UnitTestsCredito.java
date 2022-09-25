package test;

import static org.junit.jupiter.api.Assertions.*;    

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Credito;
import modelo.Cuenta;

class UnitTestsCredito {
	private Date fecha;
	private Credito credito;
	private Cuenta unaCuenta;

	@BeforeEach
	void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2020-09-15";
		dateInString = "2020-09-15";
		try {
			fecha = sdf.parse(dateInString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		credito = new Credito("334-234567-22","Daiana Di Stefano",fecha,10000);
		unaCuenta=new Cuenta("334-234567-22","Daiana Di Stefano");
	}

	@Test
	void testCompraCreditoExcedeLimite() throws Exception {
		double saldoAnterior = credito.getCreditoDisponible();
		try {
			credito.pagoEnEstablecimiento("Compra fuente 800w", 15000);
		} catch (Exception e) {
			assertTrue(saldoAnterior==credito.getCreditoDisponible(),
					"Fallo-No debe permitir hacer compras valor superior al limite");
		}
	}
	
	@Test
	void testCompraValorNegativo() throws Exception {
		double saldoAnterior = credito.getCreditoDisponible();
		try {
			credito.pagoEnEstablecimiento("Compra fuente 800w", -1000);
		} catch (Exception e) {
			assertTrue(saldoAnterior==credito.getCreditoDisponible(),
					"Fallo-No debe permitir hacer compras con monto negativo");
		}
	}
	
	@Test
	void testTraerLiquidacion() {
		try {
			credito.pagoEnEstablecimiento("Estacionamiento", 100);
			credito.pagoEnEstablecimiento("Cena", 1500);
			credito.liquidar(9, 2022);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
