package com.zikozee.springboot.cruddemo.customHealthCheck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Code {
        OK, FAIL, UNREACHABLE;

        //Mocking Code return
        private static final List<Code> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Code randomCode()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
}
