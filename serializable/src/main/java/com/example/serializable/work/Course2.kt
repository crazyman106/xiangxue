package com.example.serializable.work

import java.io.*

class Course2 : Externalizable {
     var name: String? = null
     var score = 0

    constructor()
    constructor(name: String, score: Int) {
        this.name = name
        this.score = score
    }

    @Throws(IOException::class)
    override fun writeExternal(objectOutput: ObjectOutput) {
        println("writeExterna")
        objectOutput.writeObject(name)
        objectOutput.writeInt(score)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    override fun readExternal(objectInput: ObjectInput) {
        println("readExternal")
        name = objectInput.readObject() as String
        score = objectInput.readInt()
    }

    override fun toString(): String {
        return "Course2(name=$name, score=$score)"
    }


}

fun main() {
    var course: Course2 = Course2("英语", 12);
    var out: ByteArrayOutputStream = ByteArrayOutputStream();
    var oos: ObjectOutputStream = ObjectOutputStream(out);
    oos.writeObject(course);
    val bs: ByteArray = out.toByteArray()
    oos.close();
    var ois: ObjectInputStream = ObjectInputStream(
            ByteArrayInputStream(bs));
    var course1: Course2 = ois.readObject() as Course2;
    System.out.println("course1: " + course1);
}