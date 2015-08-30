package de.ad.annotation.processing.compiler;

import de.ad.annotation.processing.api.Data;
import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

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
    messenger.printMessage(Diagnostic.Kind.NOTE, "sdfsfsdfsdfs");

    return true;
  }
}
