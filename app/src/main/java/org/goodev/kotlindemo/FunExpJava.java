package org.goodev.kotlindemo;

import kotlin.jvm.functions.Function2;

/**
 * Lambda 会编译为 Java 类， 根据参数的不同，会实现不同的
 * kotlin.jvm.functions.FunctionX 接口，
 * 最多参数个数为 22 个， Function22,
 * 如果 Lambda 参数个数多余 22 该如何处理，参考下面的文档吧
 * https://github.com/JetBrains/kotlin/blob/master/
 * spec-docs/function-types.md#functions-with-more-than-22-parameters-at-runtime
 */
final class FunExpKt$sum$1
//        extends Lambda
        implements Function2<Integer, Integer, Integer> {


    public FunExpKt$sum$1(int arity) {
//        super(arity);
    }

    @Override
    public Integer invoke(Integer x, Integer y) {
        return x + y;
    }

    static Function2 INSTANCE = new FunExpKt$sum$1(1);

}
