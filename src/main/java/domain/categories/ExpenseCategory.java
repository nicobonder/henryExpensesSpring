package domain.categories;

public class ExpenseCategory {
    private int id;
    private String categoryName;

    public ExpenseCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public ExpenseCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName.toLowerCase();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName + '\''
                ;
    }
}
