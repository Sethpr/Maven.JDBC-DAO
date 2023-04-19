package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import models.CarDTO;

public class Cars implements DAO{

    public CarDTO findById(int id){
        Connection connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM car WHERE id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    return extractCarFromResultSet(rs);
                }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

    return null;
    }

    private CarDTO extractCarFromResultSet(ResultSet rs) {
        CarDTO car;
        try {
            car = new CarDTO(rs.getInt("id"), rs.getString("make"), rs.getString("model"), rs.getInt("year"), rs.getString("color"), rs.getString("vin"));
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarDTO> findAll(){
        Connection connection = ConnectionFactory.getConnection();
    try {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM car");
        ResultSet rs = ps.executeQuery();

        List<CarDTO> cars = new ArrayList<>();

        while(rs.next())
        {
            CarDTO car = extractCarFromResultSet(rs);
            cars.add(car);
        }

        return cars;

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return null;
    }

    public CarDTO update(CarDTO dto){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE car SET make=?, model=?, year=?, color=?, vin=? WHERE id=?");
            ps.setInt(6, dto.getId());
            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setString(4, dto.getColor());
            ps.setString(5, dto.getVin());
            ps.executeUpdate();
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return dto;
    }

    public CarDTO create(CarDTO dto){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO car VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, dto.getId());
            ps.setString(2, dto.getMake());
            ps.setString(3, dto.getModel());
            ps.setInt(4, dto.getYear());
            ps.setString(5, dto.getColor());
            ps.setString(6, dto.getVin());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    return dto;
    }

    public  void delete(int id){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM car WHERE id=" + id);
             stmt.executeUpdate("DELETE FROM car WHERE id=" + id);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
