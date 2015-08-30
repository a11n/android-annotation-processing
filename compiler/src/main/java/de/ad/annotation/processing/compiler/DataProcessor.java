package de.ad.annotation.processing.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import de.ad.annotation.processing.api.Data;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class DataProcessor extends AbstractProcessor {

  private Messager messenger;

  @Override public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    messenger = processingEnv.getMessager();
  }

  @Override public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(Data.class.getCanonicalName());
  }

  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Data.class))
      process((TypeElement) annotatedElement);

    return true;
  }

  private void process(TypeElement annotatedType) {
    String packageName = getPackageNameOf(annotatedType);

    MethodSpec hello = MethodSpec.methodBuilder("hello")
        .addModifiers(Modifier.PUBLIC)
        .addParameter(String.class, "name")
        .addStatement("$T.out.println($L)", System.class, "name")
        .build();

    TypeSpec generatedClass = TypeSpec.classBuilder("_" + annotatedType.getSimpleName())
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addMethod(hello)
        .build();

    JavaFile javaFile = JavaFile.builder(packageName, generatedClass).build();
    try {
      javaFile.writeTo(processingEnv.getFiler());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getPackageNameOf(Element element) {
    return processingEnv.getElementUtils().getPackageOf(element).toString();
  }
}
