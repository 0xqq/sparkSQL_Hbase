import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.{SparkConf, SparkContext}

object Test23 {
  def main(args : Array[String]) {
    val sparkConf = new SparkConf().setMaster("spark://lxw1234.com:7077").setAppName("lxw1234.com")
    val sc = new SparkContext(sparkConf);
    var rdd1 = sc.makeRDD(Array(("A",2),("B",6),("C",7)))
    sc.hadoopConfiguration.set("hbase.zookeeper.quorum ","zkNode1,zkNode2,zkNode3")
    sc.hadoopConfiguration.set("zookeeper.znode.parent","/hbase")
    sc.hadoopConfiguration.set(TableOutputFormat.OUTPUT_TABLE,"lxw1234")
    var job = new Job(sc.hadoopConfiguration)
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    job.setOutputValueClass(classOf[Result])
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    rdd1.map(
      x => {
        var put = new Put(Bytes.toBytes(x._1))
        put.add(Bytes.toBytes("f1"), Bytes.toBytes("c1"), Bytes.toBytes(x._2))
        (new ImmutableBytesWritable,put)
      }
    ).saveAsNewAPIHadoopDataset(job.getConfiguration)
    sc.stop()
  }
}