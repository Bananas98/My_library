package com.nasirov.library.tags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.tagext.TagSupport;
import com.nasirov.library.models.Author;
import org.apache.log4j.Logger;


public class PrintAuthorsTag extends TagSupport {

    public static final Logger LOGGER = Logger.getLogger(PrintAuthorsTag.class);


    private List<Author> authors;

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int doStartTag() {
        String content="";
        for (int i = 0; i < authors.size(); i++) {
                if (authors.size()-1 == i) {
                    content+=authors.get(i).getName();
                } else {
                    content+=authors.get(i).getName() + ", ";
                }
        }
        try {
            pageContext.getOut().print(content);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return SKIP_BODY;
    }
}

