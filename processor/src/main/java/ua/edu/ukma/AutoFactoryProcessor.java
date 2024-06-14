package ua.edu.ukma;

import com.squareup.javapoet.ClassName;
import ua.edu.ukma.ElementInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

public class AutoFactoryProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<ClassName, List<ElementInfo>> result = new HashMap<>();
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(StationFactory.class)) {
//            if (annotatedElement.getKind() != ElementKind.INTERFACE) {
//                error("Only interface can be annotated with AutoFactory", annotatedElement);
//                return true;
//            }
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            //System.out.println("processing " + className.toString());
            if (!result.containsKey(className)) {
                result.put(className, new ArrayList<>());
            }
        }
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(StationElement.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error("Only class can be annotated with AutoElement", annotatedElement);
                return true;
            }
            StationElement autoElement = annotatedElement.getAnnotation(StationElement.class);
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            TypeMirror typeMirror = typeElement.getSuperclass();
            ClassName typeName = getName(typeMirror);
            result.get(typeName).add(new ElementInfo(autoElement.value(), className));
        }
        try {
            new StationBuilder(filer, result).generate();
        } catch (IOException e) {
            error(e.getMessage());
        }
        return true;
    }

    private ClassName getName(TypeMirror typeMirror) {
        String rawString = typeMirror.toString();
        int dotPosition = rawString.lastIndexOf(".");
        String packageName = rawString.substring(0, dotPosition);
        String className = rawString.substring(dotPosition + 1);
        return ClassName.get(packageName, className);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(StationFactory.class.getCanonicalName());
        annotations.add(StationElement.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(String message, Element element) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private void error(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message);
    }

    private void note(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }
}
