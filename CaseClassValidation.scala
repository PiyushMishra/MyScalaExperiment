package org.vz.datasci

object Validate {

  val keyWords = List("assert", "static", "for", "false", "package", "lazy", "this", "switch", "try", "break", "protected", "private", "volatile", ".", "goto", "float", "return", "if", "=", "do", "short", "native", "interface", "void", "strictfp", "override", "else", "synchronized", "macro", "abstract",
    "throws", "super", "=>", "yield", "implements", "finally", "public", "char", "null", "object", "match", "@", "double", "long", "<%", "import", "boolean", "forSome",
    "const", "implicit", "final", "then", "enum", "trait", "#", "new", "default", "int", "<-", "while", "with", "_", "true", ">:", "class", "extends", "def", "<:", "transient", "instanceof", "case", "type", ":", "catch", "throw", "sealed", "byte", "continue", "var", "val", "Boolean", "Byte", "Short", "Int", "Long", "Float", "Double", "Char", "String")

  def beanValidation(bean: Bean) = {
    val (name, fields, dependsOn) = (bean.name, bean.fields, bean.depends)
    assert(isValidIdenifier(name), s"case class name $name, is not valid class name in scala")
    assert(!isKeyword(name), s"case class name $name is not valid, reserved for scala")
    fields.foreach { field =>
      assert(isValidIdenifier(field.name), s"case class $name, field name ${field.name} is not valid field name in scala")
      assert(!isKeyword(field.name), s"case class $name field name ${field.name} is not valid, reserved for scala")
    }
    bean
  }

  def isKeyword(word: String) = keyWords.exists(_.equalsIgnoreCase(word))

  def isValidIdenifier(name: String) = {
    var start = true;
    var validIdentifier = true;
    for (b <- name.toCharArray) {
      if (start) {
        validIdentifier = validIdentifier && Character.isJavaIdentifierStart(b);
        start = false;
      } else {
        validIdentifier = validIdentifier && Character.isJavaIdentifierPart(b);
      }
    }
    validIdentifier
  }
}