package com.adel.modularruleengine.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidatorUtil {
    public static final String DELIMITER = ", ";
    public static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
//
//    private final List<Pair<String, Object>> params = new ArrayList<>();
//
//    //valids[paramName] = isValid , errorMessage
//    private final HashMap<String, Pair<Boolean, String>> passes= new HashMap<>();
//
//
//    public static ValidatorUtil build(){
//        return new ValidatorUtil();
//    }
//
//    public ValidatorUtil param(String paramName, Object pm){
//        this.params.add(Pair.of(paramName, pm));
//        return this;
//    }
//
//    public void validate(){
//        final List<String> errorMessage = new ArrayList<>();
//        boolean allPass = true;
//
//        for(Pair<String, Object> p: params){
//            final Pair<Boolean, String> _valids = passes.get(p.getKey());
//            if(!_valids.getKey()){
//                allPass = false;
//                errorMessage.add(_valids.getValue());
//            }
//        }
//
//        if(!allPass){
//            throw new IllegalArgumentException(String.join(DELIMITER, errorMessage));
//        }
//    }
//
//    private Integer lastIndex(){
//        return params.size()-1;
//    }
//
//    /**
//     * Custom Validator methods
//     * every params method must append valids and results
//     */
//
//    public ValidatorUtil isNotNull(){
//        final Pair<String, Object> p = params.get(lastIndex());
//        final boolean isValid = null != p.getValue();
//        final String errorMsg = p.getKey() + " is null";
//        passes.put(p.getKey(), Pair.of(isValid, errorMsg));
//        return this;
//    }
}
