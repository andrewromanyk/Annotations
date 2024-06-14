package ua.edu.ukma;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;


class StationBuilder {
    private Filer filer;
    private Map<ClassName, List<ElementInfo>> input;

    StationBuilder(Filer filer, Map<ClassName, List<ElementInfo>> input) {
        this.filer = filer;
        this.input = input;
    }

    void generate() throws IOException {
        for (ClassName key : input.keySet()) {
            MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder();
            methodBuilder
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(String.class, "vehicle")
                    .beginControlFlow("switch(vehicle)");

            for (ElementInfo elementInfo : input.get(key)) {
                methodBuilder
                        .addStatement("case $S: this.vehicle = new $T()", elementInfo.tag, elementInfo.className);
            }
            methodBuilder.endControlFlow();
            MethodSpec methodSpec = methodBuilder.build();
            System.out.println("AHSDJHHGAJKDHASKJDAHSIJUDHASKJDHAJKSDH");
            TypeSpec helloWorld = TypeSpec.classBuilder("Station")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addField((ClassName) input.keySet().toArray()[0], "vehicle", Modifier.PRIVATE)
                    .addMethod(methodSpec)
                    .build();
            JavaFile javaFile = JavaFile.builder(key.packageName(), helloWorld)
                    .build();

            javaFile.writeTo(filer);
        }
    }
}
