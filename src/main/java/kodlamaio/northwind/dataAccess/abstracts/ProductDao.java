package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategortDto;

public interface ProductDao extends JpaRepository<Product, Integer>{
	Product getByProductName(String productName);
	
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
	//Select * from products where product_name = abc and/or category_id =1
	
	List<Product> getByCategory_CategoryIdIn(List<Integer> categories);
	//select * from products where category_id in(1,2,3,4)

	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	//select * from products where product_name=bisey and categoryId=bisey
	
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategortDto"
			+ "(p.id, p.productName, c.categoryName) "
			+ "From Category c Inner Join c.products p")
	List<ProductWithCategortDto> getProductWithCategortDetails();
	/* 
	 select p.product_id, p.product_name, c.category_name, p.unit_price from categories c 
	 inner join products p on c.category_id = p.category_id
	 
	 left hoin
	 select p.product_id, p.product_name, c.category_name, p.unit_price from products p 
	 inner join categories c on p.category_id = c.category_id
	*/
	
}
