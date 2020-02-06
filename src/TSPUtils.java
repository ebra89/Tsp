import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TSPUtils {

    private final static Random r = new Random(100);

    static final TSPGene[] CITIES = generateDate(10);

    private TSPUtils(){
        throw new RuntimeException("No!");
    }

    private static TSPGene[] generateDate(final int numDataPoints) {

        final TSPGene[] data = new TSPGene[numDataPoints];
        for(int i = 0; i < numDataPoints;i++){
            data [i]= new TSPGene(TSPUtils.randomIndex(World.WIDTH),
                                  TSPUtils.randomIndex(World.HEIGHT));
        }
        return data;
    }

    static int randomIndex(final int limit){
        return r.nextInt(limit);
    }

    static <T> List<T>[] split(final List<T> list){

        final List<T> first = new ArrayList<>();
        final List<T> second = new ArrayList<>();

        final int size = list.size();

        IntStream.range(0,size).forEach(i -> {
            if(i<(size+1)/2){
                first.add(list.get(i));
            }else{
                second.add(list.get(i));
            }
        });
        return (List<T>[]) new List[]{first,second};

    }
}