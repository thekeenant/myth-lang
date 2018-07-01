package com.keenant.myth.lang;

import com.keenant.myth.lang.scope.MethodScope;
import java.util.List;
import java.util.stream.Collectors;
import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class Parameters {
  private final List<Parameter> list;

  public Parameters(List<Parameter> list) {
    this.list = list;
  }

  public List<Type> getParameterTypes() {
    return list.stream()
        .map(Parameter::getResolvedType)
        .collect(Collectors.toList());
  }

  public void analyze(MethodScope scope) {
    list.forEach(param -> param.analyze(scope));
  }
}
