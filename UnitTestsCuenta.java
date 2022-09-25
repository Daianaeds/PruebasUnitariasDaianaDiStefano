package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Cuenta;
import modelo.Movimiento;

class UnitTestsCuenta {
	
	private Cuenta cuenta;
	private Movimiento m= new Movimiento();
	private Date fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		cuenta = new Cuenta("12345678-00", "Di Stefano Daiana");
		cuenta.ingresar(1000);
	}

	@Test
	void testPermiteRetirarConFondosInsuficientes() {
		double saldoAnterior = cuenta.getSaldo();
		try {
			cuenta.retirar(2000);
		} catch (Exception e) {
			assertTrue(saldoAnterior == cuenta.getSaldo(), 
					"Fallo, Permitio retirar con saldo insuficiente");
		}
	}

}
