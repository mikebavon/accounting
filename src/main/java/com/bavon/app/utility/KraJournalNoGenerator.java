package com.bavon.app.utility;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Alternative
public class KraJournalNoGenerator extends JournalNoGenerator{

    @Override
    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "KRA" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(0, 1000 + 1);
    }
}
