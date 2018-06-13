package mx.com.web2lab.actions.seguridad;
import java.io.Serializable;


import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.velocity.context.Context;

/**
 */
public class BLogoutAction extends VelocityAction implements Serializable
{
    public void doPerform( RunData data, Context context ) throws Exception
    {
        if (data.getSession().getAttribute(AccessControlList.SESSION_KEY)!=null)
            data.getSession().removeAttribute(AccessControlList.SESSION_KEY);

        if(data.getUser() != null)
        {
            data.getUser().setHasLoggedIn(new Boolean(false));
            data.removeUserFromSession();
            //HttpSession objSesion = data.getSession();
            data.getSession().invalidate();
            ///objSesion.invalidate();
            data.save();
			data.getSession().removeAttribute("aObjLstMuestrasSesion");
			data.getSession().removeAttribute("CuotasOrden");
			data.getSession().removeAttribute("ListaBeans");
        }

        //data.setMessage(TurbineResources.getString("logout.message"));

        data.setScreenTemplate("/web2lab,seguridad,Login.vm");
    }
}