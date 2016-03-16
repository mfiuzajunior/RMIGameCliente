package br.edu.ifce.mflj.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Registrador {
	private ClienteRMI cliente;

	public ClienteRMI getCliente() {
		return cliente;
	}

	public void setCliente(ClienteRMI cliente) {
		registrarCliente(cliente);
	}

	private void registrarCliente( ClienteRMI cliente ){
		this.cliente = cliente;
		try {
			Naming.rebind( "rmi://localhost/" + cliente.getId(), cliente );

		}
		catch( MalformedURLException malformedURLException ) {
			System.err.println("Erro ao registrar cliente: " + malformedURLException.getMessage() );
		}
		catch( RemoteException remoteException ){
			System.err.println("Erro ao registrar cliente: " + remoteException.getMessage() );
		}
	}
}
