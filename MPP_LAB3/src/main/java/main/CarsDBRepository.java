package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;


    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        String query = "SELECT * FROM cars WHERE manufacturer = ?";
        List<Car> cars = new ArrayList<>();
        Connection conn = dbUtils.getConnection();
        try{
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, manufacturerN);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String model = rs.getString("model");
                Integer year = rs.getInt("year");
                Car car = new Car(manufacturerN, model, year);
                car.setId(id);
                cars.add(car);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE year >= ? AND year <= ?";
        Connection conn = dbUtils.getConnection();
        try{
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, min);
            statement.setInt(2, max);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String manufacturer = rs.getString("manufacturer");
                String model = rs.getString("model");
                Integer year = rs.getInt("year");
                Car car = new Car(manufacturer, model, year);
                car.setId(id);
                cars.add(car);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public void add(Car elem) {
        String query = "INSERT INTO cars (manufacturer,model,year) VALUES (?,?,?)";
        Connection con = dbUtils.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, elem.getManufacturer());
            ps.setString(2, elem.getModel());
            ps.setInt(3, elem.getYear());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer integer, Car elem) {
        String query = "UPDATE cars SET manufacturer = ?, model = ?, year = ? WHERE id = ?";
        Connection con = dbUtils.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, elem.getManufacturer());
            ps.setString(2, elem.getModel());
            ps.setInt(3, elem.getYear());
            ps.setInt(4, integer);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Car> findAll() {
        List<Car> cars=new ArrayList<Car>();
        Connection con = dbUtils.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cars");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String manufacturer = rs.getString("manufacturer");
                String model = rs.getString("model");
                Integer year = rs.getInt("year");
                Car car = new Car(manufacturer, model, year);
                car.setId(id);
                cars.add(car);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return cars;
    }
}