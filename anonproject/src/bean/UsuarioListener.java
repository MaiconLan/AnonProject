package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@ManagedBean(name = "usuarioListener")
@RequestScoped
public class UsuarioListener implements HttpSessionAttributeListener {
	static int usuarios;
	@Override
	public void attributeAdded(HttpSessionBindingEvent evt) {
		if(evt.getName().equalsIgnoreCase("usuario")) usuarios++;
	}
	@Override
	public void attributeRemoved(HttpSessionBindingEvent evt) {
		if(evt.getName().equalsIgnoreCase("usuario")) usuarios--;
	}
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}
	public int getUsuarios(){
		return usuarios;
	}
}
