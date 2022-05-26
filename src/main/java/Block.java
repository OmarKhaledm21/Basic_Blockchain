import java.util.Date;

public class Block {
    public String hash; //holds digital signature
    public String prevHash;
    private String data; //Transactions, etc...
    private long timeStamp;
    private int nonce;

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
    /* We will require miners to do proof-of-work by trying different variable values in the block until its hash starts with a certain number of 0’s. */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
