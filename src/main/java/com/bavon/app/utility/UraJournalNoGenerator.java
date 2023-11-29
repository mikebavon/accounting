package com.bavon.app.utility;

import javax.enterprise.inject.Specializes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Specializes
public class UraJournalNoGenerator  extends JournalNoGenerator{

    @Override
    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "URA" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(0, 1000 + 1);
    }
}
