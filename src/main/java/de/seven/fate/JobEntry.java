package de.seven.fate;

import de.seven.fate.dao.BaseEntity;

import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by Mario on 05.04.2016.
 */
public class JobEntry extends BaseEntity<Long> {

    private String title;
    private String description;
    private URL url;
}
