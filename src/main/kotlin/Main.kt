import attachments.*

fun main() {
    val service = WallService()

    val photo = Photo(1,1,1)
    val video = Video(2,2,"Видео")
    val audio = Audio(3,3,"Исполнитель", "Название трека")
    val doc = Document(4,4, "Документ")
    val link = Link("https://github.com/netology-code/kt-homeworks/tree/master/06_inheritance", "Котлин")

    service.addAttachment(photo)
    service.addAttachment(video)
    service.addAttachment(audio)
    service.addAttachment(doc)
    service.addAttachment(link)
    println(service.attachments.contentToString())

}