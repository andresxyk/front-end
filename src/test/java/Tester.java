
import junit.framework.*;
import com.meterware.httpunit.*;
import org.apache.turbine.util.Log;

/**
 * Perform regression testing of the
 * application.
 */
public class Tester extends TestCase
{
    static String url;

    /**
     * Create a new Tester object.
     */
    public Tester(String name)
    {
        super(name);
    }

    /**
     * Set up the tests.
     */
    protected void setUp()
    {
    }

    /**
     * Clean up the tests.
     */
    protected void tearDown()
    {
    }

    private WebForm getForm(WebResponse resp)
        throws Exception
    {
        WebForm form = resp.getForms()[1];
        assertNotNull(form);
        return form;
    }



    /**
     * Test Create, Retrieve, Update and Delete Employees.
     */
    public void testEmployeeCRUD()
        throws Exception
    {
        String page = url + "/servlet/template/app,EmployeeList.vm";

        System.out.println("page = "+page);

        WebConversation wc = new WebConversation();
        WebRequest     req = new GetMethodWebRequest( page );
        WebResponse   resp = wc.getResponse( req );
        WebForm       form = getForm(resp);
        String[] vals = form.getOptionValues("employeenum");
        assertEquals(0, vals.length);

        // Test adding one employee.
        SubmitButton sb = form.getSubmitButton("eventSubmit_doModeinsert");
        resp = form.submit(sb);
        form = getForm(resp);
        form.setParameter("ssnum", "123-456-789");
        form.setParameter("firstname", "Humberto");
        form.setParameter("lastname", "Hernandez");
        form.setParameter("sex", "M");
        form.setParameter("spouse", "Salma");
        form.setParameter("jobcode", "1");
        form.setParameter("deptnum", "2");
        sb = form.getSubmitButton("eventSubmit_doInsert");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(1, vals.length);

        // Test modify the first employee.
        form.setParameter("employeenum",vals[0]);
        sb = form.getSubmitButton("eventSubmit_doModemodify");
        resp = form.submit(sb);
        form = getForm(resp);
        assertEquals("Salma", form.getParameterValue("spouse"));
        form.setParameter("spouse", "Demi");
        sb = form.getSubmitButton("eventSubmit_doUpdate");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(1, vals.length);

        // Test delete the employee.
        form.setParameter("employeenum",vals[0]);
        sb = form.getSubmitButton("eventSubmit_doModedelete");
        resp = form.submit(sb);
        form = getForm(resp);
        assertEquals("Demi", form.getParameterValue("spouse"));
        sb = form.getSubmitButton("eventSubmit_doDelete");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(0, vals.length);
    }

    /**
     * Test searching for employees.
     */
    public void testEmployeeSearch()
        throws Exception
    {
        String page = url + "/servlet/template/app,EmployeeList.vm";
        WebConversation wc = new WebConversation();
        WebRequest     req = new GetMethodWebRequest( page );
        WebResponse   resp = wc.getResponse( req );
        WebForm       form = getForm(resp);
        assertEquals("25",form.getParameterValue("pageSize"));
        String[] vals = form.getOptionValues("employeenum");
        assertEquals(0, vals.length);

        // Test changing the pageSize and do search
        form.setParameter("pageSize", "5");
        SubmitButton sb = form.getSubmitButton("eventSubmit_doSearch");
        resp = form.submit(sb);
        form = getForm(resp);
        assertEquals("5",form.getParameterValue("pageSize"));
        vals = form.getOptionValues("employeenum");
        assertEquals(0, vals.length);

        // Test adding one employee.
        sb = form.getSubmitButton("eventSubmit_doModeinsert");
        resp = form.submit(sb);
        form = getForm(resp);
        form.setParameter("ssnum", "123-456-789");
        form.setParameter("firstname", "Humberto");
        form.setParameter("lastname", "Hernandez");
        form.setParameter("sex", "M");
        form.setParameter("spouse", "Salma");
        form.setParameter("jobcode", "1");
        form.setParameter("deptnum", "2");
        sb = form.getSubmitButton("eventSubmit_doInsert");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(1, vals.length);

        // Test adding second employee.
        sb = form.getSubmitButton("eventSubmit_doModeinsert");
        resp = form.submit(sb);
        form = getForm(resp);
        form.setParameter("ssnum", "789-876-123");
        form.setParameter("firstname", "Gerardo");
        form.setParameter("lastname", "Paniagua");
        form.setParameter("sex", "M");
        form.setParameter("spouse", "La chimoltrufia");
        form.setParameter("jobcode", "3");
        form.setParameter("deptnum", "1");
        sb = form.getSubmitButton("eventSubmit_doInsert");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(2, vals.length);

        // Test adding a third employee.
        sb = form.getSubmitButton("eventSubmit_doModeinsert");
        resp = form.submit(sb);
        form = getForm(resp);
        form.setParameter("ssnum", "423-764-423");
        form.setParameter("firstname", "William");
        form.setParameter("lastname", "Clinton");
        form.setParameter("sex", "M");
        form.setParameter("spouse", "Hilaria");
        form.setParameter("jobcode", "2");
        form.setParameter("deptnum", "1");
        sb = form.getSubmitButton("eventSubmit_doInsert");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(3, vals.length);

        // Test adding a fourth employee.
        sb = form.getSubmitButton("eventSubmit_doModeinsert");
        resp = form.submit(sb);
        form = getForm(resp);
        form.setParameter("ssnum", "789-123-324");
        form.setParameter("firstname", "Samuel");
        form.setParameter("lastname", "Llaven");
        form.setParameter("sex", "M");
        form.setParameter("spouse", "La chupitos");
        form.setParameter("jobcode", "3");
        form.setParameter("deptnum", "1");
        sb = form.getSubmitButton("eventSubmit_doInsert");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(4, vals.length);

        // Test adding a fifth employee.
        sb = form.getSubmitButton("eventSubmit_doModeinsert");
        resp = form.submit(sb);
        form = getForm(resp);
        form.setParameter("ssnum", "789-781-324");
        form.setParameter("firstname", "Vicente");
        form.setParameter("lastname", "Fox");
        form.setParameter("sex", "M");
        form.setParameter("spouse", "Martita");
        form.setParameter("jobcode", "3");
        form.setParameter("deptnum", "1");
        sb = form.getSubmitButton("eventSubmit_doInsert");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(5, vals.length);

        // Search using a jobcode == 3
        form.setParameter("equals_jobcode", "3");
        sb = form.getSubmitButton("eventSubmit_doSearch");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(3, vals.length);

        // And search lastname like "%ox%"
        form.setParameter("like_lastname", "ox");
        sb = form.getSubmitButton("eventSubmit_doSearch");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(1, vals.length);

        // And search sex == M
        form.setParameter("equals_sex", "M");
        sb = form.getSubmitButton("eventSubmit_doSearch");
        resp = form.submit(sb);
        form = getForm(resp);
        vals = form.getOptionValues("employeenum");
        assertEquals(1, vals.length);
    }

    public void testDeleteAll()
        throws Exception
    {
        String page = url + "/servlet/template/app,EmployeeList.vm";
        WebConversation wc = new WebConversation();
        WebRequest     req = new GetMethodWebRequest( page );
        WebResponse   resp = wc.getResponse( req );
        WebForm       form = getForm(resp);

        // Delete all the employees.
        String []vals = form.getOptionValues("employeenum");
        while(vals.length > 0) {
            form.setParameter("employeenum",vals[0]);
            SubmitButton sb = form.getSubmitButton("eventSubmit_doModedelete");
            resp = form.submit(sb);
            form = getForm(resp);
            sb = form.getSubmitButton("eventSubmit_doDelete");
            resp = form.submit(sb);
            form = getForm(resp);
            vals = form.getOptionValues("employeenum");
        }
    }

    public void testAddGroup()
        throws Exception
    {
    	Log.error("***********************"+url);
        String page = url + "/servlet/template/web2lab,seguridad,SeguridadGroupForm.vm";
        WebConversation wc = new WebConversation();
        WebRequest     req = new GetMethodWebRequest( page );
        WebResponse   resp = wc.getResponse( req );
		WebForm form = resp.getForms()[0];
		form.setParameter("name","test50");
		form.setParameter("name_old","test50");
		resp = form.submit();
		assertEquals(url+"/servlet/template/web2lab,seguridad,SeguridadGroupForm.vm",resp.getURL().toString());
    }

    /**
     * Create a suite of tests.
     */
    public static Test suite()
    {
        try
        {
            url = System.getProperty("httpunit.test.url", null);
            if (url == null)
            {
                throw new Error("Property httpunit.test.url is not defined");
            }

            TestSuite suite = new TestSuite();
//            suite.addTest(new Tester("testDeleteAll"));
//            suite.addTest(new Tester("testEmployeeCRUD"));
//            suite.addTest(new Tester("testEmployeeSearch"));
//			  suite.addTest(new Tester("testAddGroup"));
//			  suite.addTest(new Tester("testUpdateGroup"));
//			  suite.addTest(new Tester("testDeleteGroup"));
            return suite;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            //System.exit(1);
            return null;
        }
    }

    /**
     * Run the tests.
     */
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(suite());
    }
}
