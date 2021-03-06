import nomorescriptroot.browser.window._
import com.github.suzuki0keiichi.nomorescript.bridge._
import com.github.suzuki0keiichi.nomorescript.annotation._

class Global1 {
  alert("hello Global1")
}

@global object Global {
  def hello() = {
    alert("hoge")
  }

  hello()
}

class UsingTest {
  def using[A <: { def close() }, B](resource: A)(func: A => B): B = {
    try {
      func(resource)
    } catch {
      case e: Exception => throw e
      case _ => throw new Exception()
    } finally {
      if (resource != null) resource.close()
    }
  }
}

package com.github.suzuki0keiichi.compilertest {

  trait Trait1 {
    def trait1Def() = "trait"
  }

  class Class1 extends Trait1 {
    alert("Class1 say hello")

    def class1Def(param1: Int, param2: String) = {
      param1 * 10 / class1DefInt()
      alert("class1Def say " + param1 + param2 + trait1Def())
      if (param1 == 0) {
        "abc"
      } else {
        "def"
      }
    }

    def class1DefInt() = 10
  }

  class Class2(val val1: Int, var var1: String) {
    def class2Def(param1: Int)(f1: () => Unit) = {
      f1()
      alert(param1)
    }

    class2Def(10) {
      () =>
        alert("hello anonymouse class")
    }
  }

  abstract class Class3(val val1: String, val val2: Any, notMemberVal1: Int) {
    val val3 = 10
    var var1: String

    new Class2(10, "innerClass1 hello")
  }

  @mock class Class4() {
    def defClass4() = {}
  }

  @global object Object1 {
    alert("hello Object1")
    def object1Def(param1: Int) = {
      alert(param1)
    }
  }

  package childpackage {
    class ChildPackageClass1 {
      def childPackageClass1Def() = {
        alert("childPackageClass1Def")
      }
    }
  }
}
