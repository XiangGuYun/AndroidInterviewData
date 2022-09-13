# APT

APT(Annotation Processing Tool)，是一种处理注解的工具。

它对源代码文件进行检测找出其中的Annotation，使用Annotation进行额外的处理。 

Annotation处理器在处理Annotation时，可以根据源文件中的Annotation生成额外的源文件和其它的文件(文件具体内容由Annotation处理器的编写者决定)，APT还会编译生成的源文件和原来的源文件，将它们一起生成class文件。
