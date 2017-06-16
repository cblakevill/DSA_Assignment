/**
 * Common attributes of members under a sporting body
 */
public interface ISportMember extends ITreeMember<OrgVisitor>
{
    String getName();
    String getEmail();
    String getAddress();
    String getContact();
}
