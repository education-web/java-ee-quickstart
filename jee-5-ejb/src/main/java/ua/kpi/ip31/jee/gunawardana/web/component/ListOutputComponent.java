package ua.kpi.ip31.jee.gunawardana.web.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.List;

@FacesComponent(createTag = true, tagName = "listOutput")
public class ListOutputComponent extends UIComponentBase {
    @Override
    public String getFamily() {
        return "tk.exarus.component";
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        List<?> list = (List<?>) getAttributes().get("value");

        if (list != null && list.size() > 0) {
            ResponseWriter writer = context.getResponseWriter();
            for (int i = 0; i < list.size() - 1; i++) {
                writer.write(list.get(i).toString());
                writer.write(", ");
            }
            writer.write(list.get(list.size() - 1).toString());
        }
        super.encodeEnd(context);
    }
}
