package com.bavon.app.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class JournalNoGenerator {

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "JNL" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(0, 1000 + 1);
    }
}