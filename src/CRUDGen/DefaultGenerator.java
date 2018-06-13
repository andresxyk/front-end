import java.io.File;

public class DefaultGenerator extends mx.com.itweb.CRUDGen.StandardGenerator
{
    public void configure()
    {
        super.configure();

        context.setSourceDir(new File("../java"));
        context.setLicenseFile(new File("../../LICENSE.txt"));
        context.setBundleFile(new File("../l10n/MyBundle_en.properties"));
        context.setVMDir(new File("../webapp/templates/controlpub/screens"));
        context.setEntityClass("mx.com.itweb.controlpub.hbm.%Entity%");
        context.setActionClass("mx.com.itweb.controlpub.actions.controlpub.%Entity%Action");
        context.setToolClass("mx.com.itweb.controlpub.tools.%Entity%Tool");
        context.setFormName("controlpub,%Entity%Form.vm");
        context.setListName("controlpub,%Entity%List.vm");
        context.addMacro("title","$l10n.ADVERTISING_CONTROL");
        context.addMacro("h2_title","%title%: <em>$l10n.%ENTITIES%</em>");
    }
}
