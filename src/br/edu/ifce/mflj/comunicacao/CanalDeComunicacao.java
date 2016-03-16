package br.edu.ifce.mflj.comunicacao;

import br.edu.ifce.mflj.observer.CanalListener;
import br.edu.ifce.mflj.remote.ObjetoRemoto;

public interface CanalDeComunicacao extends ObjetoRemoto {
	void addCanalListener( CanalListener canalListener );
	void removeCanalListener( CanalListener canlaListener );
	void notifyPacoteRecebido( Pacote pacote );
	void enviarPacote( Pacote pacote );
	String getId();
}
