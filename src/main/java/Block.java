import java.util.Date;

public class Block {
    public String hash; //holds digital signature
    public String prevHash;
    private String data; //Transactions, etc...
    private long timeStamp;

    public Block(String data, String prevHash){
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /*
    To calculate the hash we need to calculate hash from all parts of the block
     */
    public String calculateHash(){
        String calculatedhash = StringUtil.applySha256(
                prevHash + Long.toString(timeStamp) + data
        );
        return calculatedhash;
    }
}
