package com.spbsu.pattern.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey Afonin on 27.03.2017.
 */
public class RegExpValidator implements Validator {

    private ThreadLocal<List<Matcher>> matchers = new ThreadLocal<>();

    public RegExpValidator() {
        List<String> patterns = RegExpReader.readPatterns(RegExpReader.getRegExp());
        matchers.set(new ArrayList<>(patterns.size()));
        for (String pattern : patterns) {
            Matcher matcher = Pattern.compile(pattern).matcher("");
            matchers.get().add(matcher);
        }
    }

    @Override
    public boolean validate(String s) {
        for (Matcher matcher : matchers.get()) {
            if (matcher.reset(s).matches())
                return true;
        }
        return false;
    }

}
