regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[^0]\d{6}/,
"exercise_3": /[^01]+/,
"exercise_4": /^[a-z]((?![^\w\.\_]).){2,20}$/,
"exercise_5": /^([0-9]{1,3}|1[0-4][0-9]{2}|1500)$/,
// "exercise_6": /(?=(\<)).*class(\s*\=\s*)[\"\'].*[\"\'](?=(\s*\/{0,1}\>))/
"exercise_6": /class(\s*\=\s*)[\"\'].*[\"\'](?=(\s*\/{0,1}\>))/
};
cssSelectors = {
"exercise_1": "item > java.class1",
"exercise_2": ".diffClass",
"exercise_3": "java tag:nth-of-type(1)",
"exercise_4": "css > item:nth-child(3)",
"exercise_5": "css > item:nth-child(1) java.class1:nth-child(2) ",
"exercise_6": "css > item:nth-child(2) >>>>:last-child",
"exercise_7": "#diffId2 .diffClass:last-child",
"exercise_8": "css > item:nth-child(2)"
};
