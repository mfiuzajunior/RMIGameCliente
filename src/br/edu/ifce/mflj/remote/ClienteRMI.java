package br.edu.ifce.mflj.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.ifce.mflj.comunicacao.CanalDeComunicacao;
import br.edu.ifce.mflj.comunicacao.Pacote;
import br.edu.ifce.mflj.observer.CanalListener;

public class ClienteRMI extends UnicastRemoteObject implements CanalDeComunicacao {

	private static final long serialVersionUID = -4544529482383350380L;

	private List<CanalListener> canalListeners;
	private ObjetoRemoto		servidor;
	private String				id;

	public ClienteRMI() throws RemoteException{
		super();

		try {
			id				= UUID.randomUUID().toString();
			canalListeners	= new ArrayList<CanalListener>();
			servidor		= (ObjetoRemoto) Naming.lookup("rmi://localhost/servidor");

		} catch( MalformedURLException malformedURLException ){
			malformedURLException.printStackTrace();

		} catch( RemoteException remoteException ){
			remoteException.printStackTrace();

		} catch( NotBoundException notBoundException ){
			notBoundException.printStackTrace();
		}
	}

	@Override
	public String getId(){
		return id;
	}

	@Override
	public void tratarPacote( Pacote pacote ){
		notifyPacoteRecebido( pacote );
	}

	@Override
	public void addCanalListener( CanalListener canalListener ){
		this.canalListeners.add( canalListener );
	}

	@Override
	public void removeCanalListener( CanalListener canlaListener ){
		this.removeCanalListener( canlaListener );
	}

	@Override
	public void notifyPacoteRecebido( Pacote pacote ){
		for( CanalListener listenerAtual : this.canalListeners ){
			listenerAtual.tratarPacoteRecebido( pacote );
		}
	}

	@Override
	public void enviarPacote( Pacote pacote ){
		try {
			servidor.tratarPacote( pacote );

		} catch( RemoteException remoteException ){
			System.err.println("erro ao enviar pacote ao servidor: " + remoteException.getMessage() );
		}
	}
}