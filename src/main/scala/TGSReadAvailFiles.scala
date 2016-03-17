/**
  * Created by arixanthos on 17/03/2016.
  */
//object TGSReadAvailFiles {

//}

/**
* Created by arixanthos on 13/03/2016.
*/

import com.amazonaws._
import com.amazonaws.auth._
import com.amazonaws.services.s3._
import com.amazonaws. services.s3.model._
import java.io._

object TGSReadAvailFiles extends App {    //by using "extends App" you do not need main and all lines will be executed
val accessKey = ""        //need to provide the keys
val secretKey = ""        //need to provide the keys

  val credentials = new BasicAWSCredentials (accessKey, secretKey)

  val bucketName = "aris-test-bucket"
  // this is where it took the name of the region:
  //val urlPrefix = "https://s3-us-west-1.amazonaws.com"
  val urlPrefix = "s3a://aris-test-bucket"

  val client = new AmazonS3Client (credentials)

  def uploadToS3 (fileName: String, uploadPathFName: String): String = {
    val fHandle = new File(fileName)
    println("file name", fHandle)
    println("file exists", fHandle.exists())

    client.putObject(bucketName, uploadPathFName, fHandle/*new File(fileName)*/)

    /*
        val acl = client.getObjectAcl (bucketName, uploadPath)
        acl.grantPermission (GroupGrantee.AllUsers, Permission.Read)
        client.setObjectAcl (bucketName, uploadPath, acl)
    */
    s"$urlPrefix / $bucketName / $uploadPathFName"
  }

  def uploadRetStr = uploadToS3 ("/Users/arixanthos/Downloads/brumby_xl.jpg", "~/Downloads")
  println ("upload success", uploadRetStr)

  /*
    def fileIsUploadedToS3 (uploadPath: String): Boolean = {
      try {
        client.getObjectMetadata ( bucketName, uploadPath)
        true
      } catch {
        case e: AmazonServiceException if e.getStatusCode == 404 = & gt;
        false
      }
    }

    def downloadFromS3 (uploadPath: String, downloadPath: String) {
      if (! fileIsUploadedToS3 (uploadPath)) {
        throw new RuntimeException (s File $ uploadPath is not uploaded!)
      }
      client. getObject (new GetObjectRequest (bucketName, uploadPath), [1,999,024] new File (downloadPath))
    }

    if (args.length & lt; 2) {
      println (“Usage: prog.jar file.dat s3 / upload / path.dat” +
      “local / download / path.dat”)
    } else {
      val Array (fileName, uploadPath, downloadPath, _ *) = args
      println (s “Uploading $ fileName …”)

      val url = uploadToS3 (fileName, uploadPath)
      println (s “Uploaded : $ url “)

      downloadFromS3 (uploadPath, downloadPath)
      println (s” Downloaded: $ downloadPath “)
    }
    */
}