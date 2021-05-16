class BankAccountProxy implements BankAccount{
    private BankAccountImpl originalBankAccount;

    BankAccountProxy(String bank, double initialDeposit) {
        this.originalBankAccount = new BankAccountImpl(bank, initialDeposit);
    }

    public String getBank() {
        return this.originalBankAccount.getBank();
    }
    
    @Override public void deposit(double amount) {
        this.originalBankAccount.deposit(amount);
    }
    
    @Override public boolean withdraw(double amount) {
        return (Company.user == User.OWNER) ? this.originalBankAccount.withdraw(amount) : false;
    }
    
    @Override public double balance() {
        return (Company.user == User.OWNER) ? this.originalBankAccount.balance() : Double.NaN;
    }
}