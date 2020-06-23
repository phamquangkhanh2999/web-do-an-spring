package application.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "tbl_product")
public class Product {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	@Id
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "short_desc")
	private String shortDesc;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductImage> productImageList = new ArrayList<>();

	@Column(name = "manufacturer")
	private String manufacturer;

	@Column(name = "model")
	private String model;

	@Column(name = "screen")
	private String screen;

	@Column(name = "resolution")
	private String resolution;

	@Column(name = "cpu")
	private String cpu;

	@Column(name = "ram")
	private String ram;

	@Column(name = "camera")
	private String camera;

	@Column(name = "pin")
	private String pin;

	@Column(name = "other")
	private String other;

	@Column(name = "create_date")
	private String createDate;

	@Column(name = "year_guaratee")
	private String yearGuaratee;

	@Column(name = "category_id", insertable = false, updatable = false)
	private int categoryId;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Rate> rateList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<SizeColor> listSizeColor = new ArrayList<>();


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductGuarantee> guaranteeArrayList = new ArrayList<>();

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public List<ProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getYearGuaratee() {
		return yearGuaratee;
	}

	public void setYearGuaratee(String yearGuaratee) {
		this.yearGuaratee = yearGuaratee;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Rate> getRateList() {
		return rateList;
	}

	public void setRateList(List<Rate> rateList) {
		this.rateList = rateList;
	}


	public List<SizeColor> getListSizeColor() {
		return listSizeColor;
	}

	public void setListSizeColor(List<SizeColor> listSizeColor) {
		this.listSizeColor = listSizeColor;
	}

	public List<ProductGuarantee> getGuaranteeArrayList() {
		return guaranteeArrayList;
	}

	public void setGuaranteeArrayList(List<ProductGuarantee> guaranteeArrayList) {
		this.guaranteeArrayList = guaranteeArrayList;
	}
}
