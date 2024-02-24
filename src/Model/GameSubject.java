package Model;

//Subject interface
public interface GameSubject {
 void registerObserver(GameObserver observer);
 void removeObserver(GameObserver observer);
 void notifyObservers();
}