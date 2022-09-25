package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Cuenta;
import modelo.Debito;

class UnitTestsDebito {
	

	Cuenta cuenta;
	Debito debito;
	private Date fecha;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2021-09-10";
		dateInString = "2021-09-10";
		try {
			fecha = sdf.parse(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cuenta = new Cuenta("233456-22","Di Stefano Daiana");
		cuenta.ingresar(5000);
		debito = new Debito("123456", "Di Stefano Daiana", fecha);
		debito.setCuenta(cuenta);
	}
	
	@Test
	void testRetirarMontoInsuficiente() {
		double saldoAnterior = cuenta.getSaldo();
		try {
			debito.retirar(10000);
		} catch (Exception e) {
			assertTrue(saldoAnterior==cuenta.getSaldo(),
					"Fallo-Permitio retirar monto insuficiente");
		}
	}

	@Test
	void testRetirarMontoNegativo() {
		double saldoAnterior = cuenta.getSaldo();
		try {
			debito.retirar(-500);
		} catch (Exception e) {
			assertTrue(saldoAnterior==cuenta.getSaldo(),
					"Fallo-Permitio retirar monto negativo");
		}
	}
}
