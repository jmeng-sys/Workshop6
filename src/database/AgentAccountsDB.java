package database;

/* =====================================================================================================================
AGENT CLASS CODED BY TRISTEN
===================================================================================================================== */
public class AgentAccountsDB
{
    private int agentId;
    private String password;
    private String username;

    public AgentAccountsDB(int agentId, String password, String username)
    {
        this.agentId = agentId;
        this.password = password;
        this.username = username;
    }

    public int getAgentId() { return agentId; }
    public void setAgentId(int agentId) { this.agentId = agentId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
