import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BasicBlockChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static void main(String[] args) {
        //add our blocks to the blockchain list
       blockchain.add( new Block("FIRST TRANSACTION","0") );
       blockchain.add( new Block("SECOND TRANSACTION",blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("THIRD TRANSACTION",blockchain.get(blockchain.size()-1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.prevHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
