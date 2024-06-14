package ua.edu.ukma;

import com.squareup.javapoet.ClassName;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class HasStringProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HasString.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                System.err.println("Only classes can be annotated with HasString");
                return false;
            }
            System.out.println(element.getEnclosedElements());
            boolean found = false;
            for (Element el : element.getEnclosedElements()) {
                if (Objects.equals(el.asType().toString(), "java.lang.String")) {
                    found = true;
                };
            }
            if (!found) {
                System.err.println("Class " + element.getSimpleName() + " does not have a field with type String.");
                return false;
            }
        }
            return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(HasString.class.getCanonicalName());
        return annotations;
    }

}
